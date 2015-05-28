package de.codecentric.bddsoap.jbehave;

import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.json.JSONException;

import de.codecentric.bddsoap.jbehave.steps.UrlShortenerSteps;

public class ProcessingUrls {

	String providedUrl;
	String returnedMessage;
	
	@Steps
	UrlShortenerSteps urlShortenerSteps;
	
	@Given("a url <providedUrl>")
	public void givenAUrl(String providedUrl) {
		this.providedUrl = providedUrl;
	}
	
	@When("I request the shortened form of this url")
	public void shortenUrl() throws JSONException {
		returnedMessage = urlShortenerSteps.shorten(providedUrl);
	}
	
	@When("I request the expanded form of this url")
    public void expandUrl() {
        returnedMessage = urlShortenerSteps.expand(providedUrl);
    }
	
	@Then("The shortened form should be <expectedUrl>")
	public void shortenedFormShouldBe(String expectedUrl) throws JSONException {
		urlShortenerSteps.response_should_contain_shortened_url(returnedMessage, expectedUrl);
	}
	
	@Then("the long form should be <expectedUrl>")
	public void longFormShouldBe(String expectedUrl) throws JSONException {
		urlShortenerSteps.response_should_contain_long_url(returnedMessage, expectedUrl);
	}
}
