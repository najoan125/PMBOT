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
			user.openPrivateChannel().queue(pc -> pc.sendMessage("����").queue());
		}
		catch(Exception ex) {}
	}
	
	public void onMessageReceived(MessageReceivedEvent event) {
		String cmd = event.getMessage().getContentRaw();
		MessageChannel send = event.getChannel();
		
		if (cmd.equals("!����") && event.getAuthor().getIdLong() == 603617602241167362L) {
			send.sendMessage("����").queue();
			try {
				Thread.sleep(1000);
				event.getJDA().shutdown();
				System.exit(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (cmd.equals("!�޽���")) {
			send.sendMessage("����: `!�޽���_������ ģ���� ID_ ������ ���� �޽���`").queue();
		}
		
		else if (cmd.startsWith("!�޽���")) {
			try {
				result = cmd.split("_");
				User user = PmBot.jda.retrieveUserById(result[1]).complete();
				String check = result[2].replaceAll("\\p{Z}", "");
				if (check.startsWith("!�޽���")) {
					send.sendMessage(":no_entry_sign: `!�޽���` �� �����ϴ� �޽����� ���� �� �����ϴ�!").queue();
					return;
				}
				user.openPrivateChannel().queue(pc -> pc.sendMessage(result[2]).queue());
				send.sendMessage("�޽����� ���������� ���½��ϴ�").queue();
			} catch(Exception e){
				send.sendMessage(":no_entry_sign: �޽����� ������ ���� ������ �߻��߽��ϴ�.\n����: `!�޽���_������ ģ���� ID_ ������ ���� �޽���`").queue();
			}
		}
	}
}
