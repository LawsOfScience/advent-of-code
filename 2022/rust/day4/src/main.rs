fn main() {
    println!("PART 1: {}", part1());
    println!("PART 2: {}", part2());
}

fn part1() -> u32 {
    let input = std::fs::read_to_string("../../input/day4.txt").unwrap();
    let jobs_list = input.lines();

    let mut final_count = 0;

    for job_pair in jobs_list {
        let jobs = job_pair.split(",").collect::<Vec<&str>>();

        let job1 = jobs.first()
            .unwrap()
            .split("-")
            .filter_map(|zone| zone.parse::<u32>().ok())
            .collect::<Vec<u32>>();
        let job2 = jobs.last()
            .unwrap()
            .split("-")
            .filter_map(|zone| zone.parse::<u32>().ok())
            .collect::<Vec<u32>>();

        final_count += (
            (job1.first().unwrap() <= job2.first().unwrap() && job1.last().unwrap() >= job2.last().unwrap())
            ||
            (job1.first().unwrap() >= job2.first().unwrap() && job1.last().unwrap() <= job2.last().unwrap())
        ) as u32;
    }

    final_count
}

fn part2() -> u32 {
    let input = std::fs::read_to_string("../../input/day4.txt").unwrap();
    let jobs_list = input.lines();

    let mut final_count = 0;

    for job_pair in jobs_list {
        let jobs = job_pair.split(",").collect::<Vec<&str>>();

        let job1 = jobs.first()
            .unwrap()
            .split("-")
            .filter_map(|zone| zone.parse::<u32>().ok())
            .collect::<Vec<u32>>();
        let job2 = jobs.last()
            .unwrap()
            .split("-")
            .filter_map(|zone| zone.parse::<u32>().ok())
            .collect::<Vec<u32>>();

        final_count += (
            (job1.first().unwrap() <= job2.last().unwrap() && job1.last().unwrap() >= job2.first().unwrap())
            ||
            (job1.last().unwrap() >= job2.first().unwrap() && job1.first().unwrap() <= job2.last().unwrap())
        ) as u32;
    }

    final_count
}
