package com.thoughtworks.Demo;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * 主程序类
 * 
 * @author Dan
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		List<User> users = new ArrayList<>();
		while (true) {
			String input = s.nextLine();
			if (input.equals("")) {
				break;
			} else if (isValid(input)) {
				User u = StringToUser(input);
				if (users.size() == 0 || users == null) {
					users.add(u);
					System.out.println("Success: the booking is accepted!");
				} else {
					ListIterator<User> iterator = users.listIterator();

					while (iterator.hasNext()) {

						User user = iterator.next();

						if (u.getChoice().getName().equals(user.getChoice().getName())) {
							if (TimeUtil.IsConflict(u.getStartTime(), user.getStartTime(), u.getEndTime(),
									user.getEndTime())) {
								if (!user.isCancel() && u.isCancel()) {
									iterator.set(u);
									System.out.println("Success: the booking is accepted!");
								} else if (user.isCancel() && u.isCancel()) {
									System.out.println("Error: the booking being cancelled does not exist!");
								} else if (user.isCancel() && !u.isCancel()) {
									iterator.add(u);
									System.out.println("Success: the booking is accepted!");
								} else {
									System.out.println("Error: the booking conflicts with existing bookings!");
								}

							}
						} else {
							iterator.add(u);
							System.out.println("Success: the booking is accepted!");
							break;
						}
					}
				}
			} else {
				System.out.println("Error: the booking is invalid!");
			}
		}
		System.out.println("收⼊汇总");
		System.out.println("---");
		GetTotal(users);
	}

	private static void GetTotal(List<User> users) throws Exception {
		Collections.sort(users);
		int totalA = 0;
		int totalB = 0;
		int totalC = 0;
		int totalD = 0;
		System.out.println("场地:A");
		for (User user : users) {
			if (user.getChoice().getName().equals("A")) {
				if (user.isCancel()) {
					totalA += user.getLiquidatedDamages();
				} else {
					totalA += user.getCharge();
				}
				System.out.println(user.toString());
			}

		}
		System.out.println("⼩计： " + totalA + "元");
		System.out.println();

		System.out.println("场地:B");
		for (User user : users) {
			if (user.getChoice().getName().equals("B")) {

				if (user.isCancel()) {
					totalB += user.getLiquidatedDamages();
				} else {
					totalB += user.getCharge();
				}
				System.out.println(user.toString());
			}
		}
		System.out.println("⼩计： " + totalB + "元");
		System.out.println();

		System.out.println("场地:C");
		for (User user : users) {
			if (user.getChoice().getName().equals("C")) {

				if (user.isCancel()) {
					totalC += user.getLiquidatedDamages();
				} else {

					totalC += user.getCharge();
				}
				System.out.println(user.toString());
			}

		}
		System.out.println("⼩计： " + totalC + "元");
		System.out.println();

		System.out.println("场地:D");
		for (User user : users) {
			if (user.getChoice().getName().equals("D")) {

				if (user.isCancel()) {
					totalD += user.getLiquidatedDamages();
				} else {
					totalD += user.getCharge();
				}
				System.out.println(user.toString());
			}

		}
		System.out.println("⼩计： " + totalD + "元");

		System.out.println("---");
		System.out.println("总计：" + (totalA + totalB + totalC + totalD) + "元");

	}

	private static boolean isValid(String input) throws ParseException {
		boolean result = false;
		String[] userInfo = input.split(" ");
		if (userInfo.length == 4 || userInfo.length == 5) {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			String start = userInfo[2].substring(0, userInfo[2].indexOf('~'));
			String end = userInfo[2].substring(userInfo[2].indexOf('~') + 1, userInfo[2].length());
			Date startTime = sdf.parse(start);
			Date endTime = sdf.parse(end);
			if (TimeUtil.IsWhole(startTime) && TimeUtil.IsWhole(endTime) && (endTime.compareTo(startTime) == 1)) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}

	private static User StringToUser(String input) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
		User u = new User();
		Court court = new Court();
		String[] userInfo = input.split(" ");

		if (userInfo.length == 4) {
			u.setUserId(userInfo[0]);
			u.setBookDay(sdf.parse(userInfo[1]));
			u.setStartTime(sdf2.parse(userInfo[2].substring(0, userInfo[2].indexOf('~'))));
			u.setEndTime(sdf2.parse(userInfo[2].substring(userInfo[2].indexOf('~') + 1, userInfo[2].length())));
			court.setName(userInfo[3]);
			court.setChargeByDate(u.getBookDay(), u.getStartTime(), u.getEndTime());
			u.setChoice(court);
			u.setCancel(false);
			u.setCharge();
			u.setLiquidatedDamages(u.getCharge());
		} else if (userInfo.length == 5) {
			u.setUserId(userInfo[0]);
			u.setBookDay(sdf.parse(userInfo[1]));
			u.setStartTime(sdf2.parse(userInfo[2].substring(0, userInfo[2].indexOf('~'))));
			u.setEndTime(sdf2.parse(userInfo[2].substring(userInfo[2].indexOf('~') + 1, userInfo[2].length())));
			court.setName(userInfo[3]);
			court.setChargeByDate(u.getBookDay(), u.getStartTime(), u.getEndTime());
			u.setChoice(court);
			if (userInfo[4].equals("C")) {
				u.setCancel(true);
			}
			u.setCharge();
			u.setLiquidatedDamages(u.getCharge());
		}
		return u;
	}

}
