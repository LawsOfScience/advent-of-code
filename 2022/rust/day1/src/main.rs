use itertools::Itertools;

fn main() {
    println!("PART 1: {}", part1());
    println!("PART 2: {}", part2());
}

fn part1() -> u32 {
    let input = std::fs::read_to_string("../../input/day1.txt").unwrap();
    let calorie_lines = input.split("\n\n");

    let mut sums: Vec<u32> = vec![];
    for group in calorie_lines {
        let mut group_sum = 0;

        for calorie_amount in group.lines() {
            if let Ok(value) = calorie_amount.parse::<u32>() {
                group_sum += value;
            }
        }

        sums.push(group_sum);
    }

    *sums.iter().max().unwrap()
}

fn part2() -> u32 {
    let input = std::fs::read_to_string("../../input/day1.txt").unwrap();
    let calorie_lines = input.split("\n\n");

    let mut sums: Vec<u32> = vec![];
    for group in calorie_lines {
        let mut group_sum = 0;

        for calorie_amount in group.lines() {
            if let Ok(value) = calorie_amount.parse::<u32>() {
                group_sum += value;
            }
        }

        sums.push(group_sum);
    }

    let mut final_sum = 0;
    for _ in 0..3 {
        let posn_of_max = sums.iter().position_max().unwrap();
        let max = sums.remove(posn_of_max);
        final_sum += max;
    }

    final_sum
}

