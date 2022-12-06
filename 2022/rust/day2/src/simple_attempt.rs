/*
    Rock:       A  X
    Paper:      B  Y
    Scissors:   C  Z
*/
fn main() {
    println!("{}", part1());
}

fn part1() -> u32 {
    let strategy = std::fs::read_to_string("../../input/day2.txt").unwrap();

    let mut total_sum = 0;
    for round in strategy.split("\n") {
        if round == "" { break; }

        let round_vec = round.split(" ").collect::<Vec<&str>>();
        let first_play = round_vec[0];
        let second_play = round_vec[1];

        let mut round_sum = 0;
        if first_play == "A" {
            // rock
            round_sum += 1;

            if second_play == "X" {
                round_sum += 3;
            } else if second_play == "Z" {
                round_sum += 6;
            }
        } else if first_play == "B" {
            // paper
            round_sum += 2;

            if second_play == "Y" {
                round_sum += 3;
            } else if second_play == "X" {
                round_sum += 6;
            }
        } else if first_play == "C" {
            // scissors
            round_sum += 3;

            if second_play == "Z" {
                round_sum += 3;
            } else if second_play == "Y" {
                round_sum += 6;
            }
        }

        total_sum += round_sum;
    }

    total_sum
}
