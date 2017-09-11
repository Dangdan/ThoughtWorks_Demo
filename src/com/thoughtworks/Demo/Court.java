package com.thoughtworks.Demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 场地类
 * 
 * @author Dan
 *
 */
public class Court {
	private String name; // 场地名称
	private int charge; // 场地收费

	public String getName() {
		return this.name;
	}

	/**
	 * 设置正确名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		if (name.equals("A") || name.equals("B") || name.equals("C") || name.equals("D")) {
			this.name = name;
		}

	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	/**
	 * 通过不同区间日期计算所需费用
	 * 
	 * @param bookDay
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public int setChargeByDate(Date bookDay, Date startTime, Date endTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date h9 = sdf.parse("9:00");
		Date h12 = sdf.parse("12:00");
		Date h18 = sdf.parse("18:00");
		Date h20 = sdf.parse("20:00");
		Date h22 = sdf.parse("22:00");

		Calendar cal = Calendar.getInstance();
		cal.setTime(bookDay);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		if (week != Calendar.SATURDAY && week != Calendar.SUNDAY) {

			if (TimeUtil.LRM(h9, h12, startTime) && TimeUtil.LRM(h9, h12, endTime)) {
				this.charge = (TimeUtil.getHours(startTime, endTime)) * 30;
			}

			else if (TimeUtil.LRM(h9, h12, startTime) && TimeUtil.LRM(h12, h18, endTime)) {
				this.charge = (TimeUtil.getHours(startTime, h12)) * 30 + TimeUtil.getHours(h12, endTime) * 50;
			}

			else if (TimeUtil.LRM(h9, h12, startTime) && TimeUtil.LRM(h18, h20, endTime)) {
				this.charge = (TimeUtil.getHours(h9, startTime)) * 30 + 6 * 50 + TimeUtil.getHours(h18, endTime) * 80;
			}

			else if (TimeUtil.LRM(h9, h12, startTime) && TimeUtil.LRM(h20, h22, endTime)) {
				this.charge = (TimeUtil.getHours(h9, startTime)) * 30 + 6 * 50 + 2 * 80
						+ TimeUtil.getHours(h20, endTime) * 60;
			}

			else if (TimeUtil.LRM(h12, h18, startTime) && TimeUtil.LRM(h12, h18, endTime)) {
				this.charge = (TimeUtil.getHours(startTime, endTime)) * 50;
			}

			else if (TimeUtil.LRM(h12, h18, startTime) && TimeUtil.LRM(h18, h20, endTime)) {
				this.charge = (TimeUtil.getHours(startTime, h18)) * 50 + TimeUtil.getHours(h18, endTime) * 80;
			}

			else if (TimeUtil.LRM(h12, h18, startTime) && TimeUtil.LRM(h20, h22, endTime)) {
				this.charge = (TimeUtil.getHours(startTime, h18)) * 50 + 2 * 80 + TimeUtil.getHours(h20, endTime) * 60;
			}

			else if (TimeUtil.LRM(h18, h20, startTime) && TimeUtil.LRM(h18, h20, endTime)) {
				this.charge = (TimeUtil.getHours(startTime, endTime)) * 80;
			}

			else if (TimeUtil.LRM(h18, h20, startTime) && TimeUtil.LRM(h20, h22, endTime)) {
				this.charge = (TimeUtil.getHours(startTime, h20)) * 80 + TimeUtil.getHours(h20, endTime) * 60;
			}

			else if (TimeUtil.LRM(h20, h22, startTime) && TimeUtil.LRM(h20, h22, endTime)) {
				this.charge = (TimeUtil.getHours(startTime, endTime)) * 60;
			}
		} else {
			if (TimeUtil.LRM(h9, h12, startTime) && TimeUtil.LRM(h9, h12, endTime)) {
				this.charge = (TimeUtil.getHours(startTime, endTime)) * 40;
			}

			else if (TimeUtil.LRM(h9, h12, startTime) && TimeUtil.LRM(h12, h18, endTime)) {
				this.charge = (TimeUtil.getHours(startTime, h12)) * 40 + TimeUtil.getHours(h12, endTime) * 50;
			}

			else if (TimeUtil.LRM(h9, h12, startTime) && TimeUtil.LRM(h18, h22, endTime)) {
				this.charge = (TimeUtil.getHours(h9, startTime)) * 40 + 6 * 50 + TimeUtil.getHours(h18, endTime) * 60;
			}

			else if (TimeUtil.LRM(h12, h18, startTime) && TimeUtil.LRM(h12, h18, endTime)) {
				this.charge = (TimeUtil.getHours(startTime, endTime)) * 50;
			}

			else if (TimeUtil.LRM(h12, h18, startTime) && TimeUtil.LRM(h18, h22, endTime)) {
				this.charge = (TimeUtil.getHours(startTime, h18)) * 50 + TimeUtil.getHours(h18, endTime) * 60;
			}

			else if (TimeUtil.LRM(h18, h22, startTime) && TimeUtil.LRM(h18, h22, endTime)) {
				this.charge = (TimeUtil.getHours(startTime, endTime)) * 60;
			}

		}
		return this.charge;
	}

}
