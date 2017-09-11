package com.thoughtworks.Demo;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户类
 * 
 * @author Dan
 *
 */
public class User implements Comparable<User> {
	private String userId; // 用户ID
	private Date bookDay; // 预定日期
	private Date startTime; // 预定起始时间
	private Date endTime; // 预定结束时间
	private Court choice; // 场地选择
	private boolean isCancel; // 是否要取消
	private int charge; // 在场地产生的金额
	private int liquidatedDamages; // 违约金

	public User() {
	}

	// 计算场地所产生的金额
	public int getCharge() throws Exception {
		return this.charge;
	}

	public void setCharge() throws ParseException {
		this.charge = this.choice.setChargeByDate(this.bookDay, this.startTime, this.endTime);
	}

	/**
	 * 根据收费算违约金
	 * 
	 * @param charge
	 * @throws Exception
	 */
	public void setLiquidatedDamages(int charge) throws Exception {
		if (this.isCancel) {
			Calendar c = Calendar.getInstance();
			c.setTime(this.bookDay);
			int week = c.get(Calendar.DAY_OF_WEEK);
			if (week == Calendar.SATURDAY || week == Calendar.SUNDAY) {
				this.liquidatedDamages = (int) (charge * 0.25);
			}
			this.liquidatedDamages = (int) (charge * 0.5);
		}
	}

	public String toString() {

		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
		String newBookDay = day.format(bookDay);
		SimpleDateFormat time = new SimpleDateFormat("HH:mm");
		String newStartTime = time.format(startTime);
		String newEndTime = time.format(endTime);
		String result = "";
		if (this.isCancel) {
			result = userId + " " + newBookDay + " " + newStartTime + "~" + newEndTime + " " + "违约金" + " "
					+ this.getLiquidatedDamages() + "元";
		} else {
			try {
				result = userId + " " + newBookDay + " " + newStartTime + "~" + newEndTime + " " + this.getCharge()
						+ "元";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;

	}

	public int getLiquidatedDamages() {

		return this.liquidatedDamages;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getBookDay() {
		return bookDay;
	}

	public void setBookDay(Date date) {
		this.bookDay = date;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Court getChoice() {
		return choice;
	}

	public void setChoice(Court choice) {
		this.choice = choice;
	}

	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}

	@Override
	public int compareTo(User o) {
		if (this.getBookDay().compareTo(o.getBookDay()) == 0) {
			return this.getStartTime().compareTo(o.getStartTime());
		} else {
			return this.getBookDay().compareTo(o.getBookDay());
		}
	}

}
