package net.ruxion.util;

public class TimeUtil
{
	public static String getTime(int seconds)
	{
		int startsec = seconds;
        int hours = startsec / 3600;
        int rolloverseconds = startsec % 3600;
        int minutes = rolloverseconds / 60;
        int endseconds = rolloverseconds % 60;
        return hours+" hours, "+minutes+" minutes, "+endseconds+"";
	}
}