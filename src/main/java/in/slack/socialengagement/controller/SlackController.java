package in.slack.socialengagement.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.slack.socialengagement.model.SlackPayload;
import in.slack.socialengagement.service.SlackService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1.0/slack")
public class SlackController {

	@Autowired
	private SlackService slackService;

	@PostMapping("/sl/callback/webhooks")
	public ResponseEntity<?> eventHandler(HttpServletRequest request, String data) throws Exception {
		log.info("-----Webhook method to retrieve events. from the post-----");
		String pushedJsonAsString = IOUtils.toString(request.getInputStream(), "utf-8");

		log.info("------Event from webhook : {}", pushedJsonAsString);
		JSONObject entries = (JSONObject) new JSONParser().parse(pushedJsonAsString);

		log.info("----Challenge : {}", (String) entries.get("challenge"));

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ModelMap().addAttribute("challenge", (String) entries.get("challenge")));

	}

	@PostMapping("/post")
	public ResponseEntity<?> postToChannel(@RequestBody SlackPayload slackPayload) throws IOException {

		return ResponseEntity.status(HttpStatus.OK)
				.body(slackService.postMessageToChannel(slackPayload.getWebhookUrl(), slackPayload.getTextMessage()));

	}
}
