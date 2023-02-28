package in.slack.socialengagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class SlackPayload implements Serializable {

	private String textMessage;
	private String webhookUrl;
}