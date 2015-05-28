Narrative:
In order to display a link to a shortened URL correctly
As an web site author
I want to be able to expand shortened URLs into their long form

Scenario: Expanding URLs
Given a url <providedUrl>
When I request the expanded form of this url
Then the long form should be <expectedUrl>
Examples:
| providedUrl        | expectedUrl             |
| http://goo.gl/fbsS | http://www.google.com/  |
| http://goo.gl/xj57 | http://www.amazon.com/  |