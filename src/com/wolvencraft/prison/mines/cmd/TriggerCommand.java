package com.wolvencraft.prison.mines.cmd;

import org.bukkit.ChatColor;

import com.wolvencraft.prison.mines.PrisonMine;
import com.wolvencraft.prison.mines.mine.Mine;
import com.wolvencraft.prison.mines.util.Message;
import com.wolvencraft.prison.mines.util.Util;

public class TriggerCommand implements BaseCommand {

	public boolean run(String[] args) {
		
		if(args.length == 1) {
			getHelp();
			return true;
		}
		
		if(args.length != 3) {
			Message.sendError(PrisonMine.getLanguage().ERROR_ARGUMENTS);
			return false;
		}
		
		Mine curMine = PrisonMine.getCurMine();
		if(curMine == null) {
			Message.sendError(PrisonMine.getLanguage().ERROR_MINENOTSELECTED);
			return false;
		}
		
		if(args[1].equalsIgnoreCase("time")) {
			if(args[2].equalsIgnoreCase("toggle")) {
				if(curMine.getAutomaticReset()) {
					curMine.setAutomaticReset(false);
					Message.sendCustom(curMine.getName(), "Time trigger is " + ChatColor.RED + "off");
				}
				else {
					curMine.setAutomaticReset(true);
					Message.sendCustom(curMine.getName(), "Time trigger is " + ChatColor.GREEN + "on");
				}
			} else {
				int time = Util.parseTime(args[2]);
				if(time <= 0) {
					Message.sendError("Invalid time provided");
					return false;
				}
				curMine.setResetPeriod(time);
				String parsedTime = Util.parseSeconds(time);
				Message.sendCustom(curMine.getName(), "Mine will now reset every " + ChatColor.GOLD + parsedTime + ChatColor.WHITE + " minute(s)");
			}
		} else if(args[1].equalsIgnoreCase("composition")) {
			if(args[2].equalsIgnoreCase("toggle")) {
				if(curMine.getCompositionReset()) {
					curMine.setCompositionReset(false);
					Message.sendCustom(curMine.getName(), "Composition trigger is " + ChatColor.RED + "off");
				}
				else {
					curMine.setCompositionReset(true);
					Message.sendCustom(curMine.getName(), "Composition trigger is " + ChatColor.GREEN + "on");
				}
			} else {
				
			}
		} else if(args[1].equalsIgnoreCase("global")) {
			if(args[2].equalsIgnoreCase("toggle")) {
				
			} else {
				
			}
		} else {
			Message.sendError(PrisonMine.getLanguage().ERROR_COMMAND);
			return false;
		}
		
		return curMine.save();
	}

	public void getHelp() {
		Message.formatHeader(20, "Trigger");
		Message.formatHelp("trigger", "time toggle", "Toggles the timer on and off");
		Message.formatHelp("trigger", "time <time>", "Sets the timer to the specified value");
		Message.formatHelp("trigger", "composition toggle", "Toggles the composition trigger");
		Message.formatHelp("trigger", "composition <percent>", "Sets the composition percent");
		Message.formatHelp("trigger", "global toggle", "Toggles the global timer");
		Message.formatHelp("trigger", "global <time>", "Sets the global timer");
	}

	public void getHelpLine() { Message.formatHelp("trigger", "", "Shows the reset trigger help page"); }

}
