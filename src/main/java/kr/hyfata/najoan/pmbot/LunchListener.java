package kr.hyfata.najoan.pmbot;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LunchListener extends ListenerAdapter {
	
	private String[] result;
	public LunchListener(PmBot bot) {
	}
	
	@Override
	public void onReady(ReadyEvent e) {
		try {
			User user = PmBot.jda.retrieveUserById(603617602241167362L).complete();
			user.openPrivateChannel().queue(pc -> pc.sendMessage("켜짐").queue());
		}
		catch(Exception ex) {}
	}
	
	public void onMessageReceived(MessageReceivedEvent event) {
		String cmd = event.getMessage().getContentRaw();
		MessageChannel send = event.getChannel();
		
		if (cmd.equals("!끄기") && event.getAuthor().getIdLong() == 603617602241167362L) {
			send.sendMessage("꺼짐").queue();
			try {
				Thread.sleep(1000);
				event.getJDA().shutdown();
				System.exit(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (cmd.equals("!메시지")) {
			send.sendMessage("사용법: `!메시지_전송할 친구의 ID_ 보내고 싶은 메시지`").queue();
		}
		
		else if (cmd.startsWith("!메시지")) {
			try {
				result = cmd.split("_");
				User user = PmBot.jda.retrieveUserById(result[1]).complete();
				String check = result[2].replaceAll("\\p{Z}", "");
				if (check.startsWith("!메시지")) {
					send.sendMessage(":no_entry_sign: `!메시지` 로 시작하는 메시지는 보낼 수 없습니다!").queue();
					return;
				}
				user.openPrivateChannel().queue(pc -> pc.sendMessage(result[2]).queue());
				send.sendMessage("메시지를 성공적으로 보냈습니다").queue();
			} catch(Exception e){
				send.sendMessage(":no_entry_sign: 메시지를 보내는 도중 오류가 발생했습니다.\n사용법: `!메시지_전송할 친구의 ID_ 보내고 싶은 메시지`").queue();
			}
		}
	}
}
