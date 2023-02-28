package in.slack.socialengagement.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SlackService {

	/**
	 * A webhook url contains the details of workplace, channel, and credentials.
	 * 
	 * @param webhookUrl
	 * @param textMessage
	 * @return
	 * @throws IOException
	 */
	public WebhookResponse postMessageToChannel(String webhookUrl, String textMessage) throws IOException {
		Slack slack = Slack.getInstance();

		Payload payload = Payload.builder().text(textMessage).build();

		WebhookResponse response = slack.send(webhookUrl, payload);
		log.info("-----Response : {}", response);

		return response;
	}
}
