Narrative:
In order to fit long URLs into short text messages
As an internet user
I want to be able to represent URLs in a shortened form

Scenario: Shorten Urls
Given a url <providedUrl>
When I request the shortened form of this url
Then The shortened form should be <expectedUrl>
Examples:
| providedUrl             | expectedUrl        |
| http://www.google.com/  | http://goo.gl/fbsS |
| http://www.amazon.com/  | http://goo.gl/xj57 |