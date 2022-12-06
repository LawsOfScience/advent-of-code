/*
    Rock:       A  X (or lose)
    Paper:      B  Y (or draw)
    Scissors:   C  Z (or win)
*/
fn main() {
    println!("PART 1: {}", part1());
    println!("PART 2: {}", part2());
}

fn part1() -> u32 {
    let strategy = std::fs::read_to_string("../../input/day2.txt").unwrap();

    strategy
        .split("\n")
        .filter_map(|stratagem| stratagem.into_round())
        .map(|round| round.get_result_part1())
        .sum::<u32>()
}

fn part2() -> u32 {
    let strategy = std::fs::read_to_string("../../input/day2.txt").unwrap();

    strategy
        .split("\n")
        .filter_map(|stratagem| stratagem.into_round())
        .map(|round| round.get_result_part2())
        .sum::<u32>()
}

#[derive(Debug)]
struct Round {
    me: PlayType,
    elf: PlayType,
}

impl Round {
    fn get_result_part1(&self) -> u32 {
        let mut score = self.me as u32;

        let result = match &self.me {
            PlayType::Rock =>
                match &self.elf {
                    PlayType::Rock => 3,
                    PlayType::Paper => 0,
                    PlayType::Scissors => 6,
                },
            PlayType::Paper =>
                match &self.elf {
                    PlayType::Rock => 6,
                    PlayType::Paper => 3,
                    PlayType::Scissors => 0,
                },
            PlayType::Scissors =>
                match &self.elf {
                    PlayType::Rock => 0,
                    PlayType::Paper => 6,
                    PlayType::Scissors => 3,
                }
        };
        score += result;

        score
    }

    fn get_result_part2(&self) -> u32 {
        match &self.me {
            // For part 2:
            // X (rock in part 1)           -> lose,
            // Y (paper in part 1)          -> draw,
            // and Z (scissors in part 1)   -> win
            PlayType::Rock => match &self.elf {
                PlayType::Rock => 0+3,
                PlayType::Paper => 0+1,
                PlayType::Scissors => 0+2,
            },
            PlayType::Paper => match &self.elf {
                PlayType::Rock => 3+1,
                PlayType::Paper => 3+2,
                PlayType::Scissors => 3+3,
            },
            PlayType::Scissors => match &self.elf {
                PlayType::Rock => 6+2,
                PlayType::Paper => 6+3,
                PlayType::Scissors => 6+1,
            },
        }
    }
}

#[derive(PartialEq, Eq, Clone, Copy, Debug)]
enum PlayType {
    Rock = 1,
    Paper = 2,
    Scissors = 3,
}

trait IntoRound {
    fn into_round(&self) -> Option<Round>;
}

impl IntoRound for &str {
    fn into_round(&self) -> Option<Round> {
        // cause of that one stupid blank line at the end of the file that won't go away
        if *self == "" { return None; }

        let plays = self
            .split(" ")
            .filter_map(|play| {
                if play == "A" || play == "X" {
                    return Some(PlayType::Rock);
                } else if play == "B" || play == "Y" {
                    return Some(PlayType::Paper);
                } else if play == "C" || play == "Z" {
                    return Some(PlayType::Scissors);
                }

                None
            })
            .collect::<Vec<PlayType>>();

				// I had these reversed....no wonder I wasn't getting the right
				// answers in part one.
        Some(Round {
            me: *plays.last().unwrap(),
            elf: *plays.first().unwrap(),
        })
    }
}
