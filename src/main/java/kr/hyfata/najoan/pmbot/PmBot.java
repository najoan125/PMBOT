package kr.hyfata.najoan.pmbot;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class PmBot {
	static PmBot bot = new PmBot();
	public static JDA jda;
	private final ScheduledExecutorService threadpool;
	public static void main(String[] args) throws LoginException {
		jda = JDABuilder.createDefault("ODAwOTA5MTkwMzY0Mzk3NTg5.YAY-zw.H18ZXxhXQL0lb9hL9qjBHCsFDaE").setActivity(Activity.playing("쌉천재니까 무시하지마 이새꺄"))
				.enableCache(CacheFlag.MEMBER_OVERRIDES)
				.disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.EMOTE, CacheFlag.ONLINE_STATUS)
				.setBulkDeleteSplittingEnabled(true)
				.build();
		jda.addEventListener(new LunchListener(bot));
	}
	
	public JDA getJDA() {
		return jda;
	}
	
	public PmBot(){
		this.threadpool = Executors.newSingleThreadScheduledExecutor();
	}
	public ScheduledExecutorService getThreadpool() {
		return threadpool;
	}
}
