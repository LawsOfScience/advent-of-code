use std::collections::HashSet;

fn main() {
    println!("PART 1: {}", part1());
    println!("PART 2: {}", part2());
}

fn find_item_value(item: char) -> u32 {
    if item == '%' { panic!("Didn't find a duplicate in a bag"); }


    let mut item_list = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".chars();
    let item_value = item_list.position(|check| check == item).unwrap() + 1;

    item_value as u32
}

fn part1() -> u32 {
    let input = std::fs::read_to_string("../../input/day3.txt").unwrap();

    let mut final_sum = 0;

    for bag in input.lines() {
        let mut bag_item_set: HashSet<char> = HashSet::new();
        let mut duplicate_item: char = '%';

        let (compartment1, compartment2) = bag.split_at(bag.len() / 2);
        compartment1.chars().for_each(|item| { bag_item_set.insert(item); });
        for item in compartment2.chars() {
            if bag_item_set.contains(&item) {
                duplicate_item = item;
                break;
            }
        }

        final_sum += find_item_value(duplicate_item);
    }

    final_sum
}

fn part2() -> u32 {
    let input = std::fs::read_to_string("../../input/day3.txt").unwrap();
    let elves = input
        .lines()
        .collect::<Vec<&str>>();
    let elf_groups = elves.chunks_exact(3);

    let mut final_sum = 0;

    for group in elf_groups {
        let elf1_bag = group[0].chars().collect::<HashSet<char>>();
        let elf2_bag = group[1].chars().collect::<HashSet<char>>();
        let elf3_bag = group[2].chars().collect::<HashSet<char>>();

        let intersection_1_2 = elf1_bag.intersection(&elf2_bag).collect::<HashSet<&char>>();
        let intersection_2_3 = elf3_bag.intersection(&elf2_bag).collect::<HashSet<&char>>();
        let common_item = intersection_1_2.intersection(&intersection_2_3).next().unwrap();

        final_sum += find_item_value(**common_item);
    }

    final_sum
}
