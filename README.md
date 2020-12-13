#### RSS Feed Reader

This application fetches the 10 latest topics from [https://xkcd.com/json.html][XKCD] and [http://feeds.feedburner.com/PoorlyDrawnLines][PDL] 
and combines them in a JSON format based on latest published date. The application uses REST Template to read data from XKCD and ROME api to fetch
PDL. They are composed in two Spring components and will be combined in FeedController.

The docker file will build and run application.


[XKCD]: https://xkcd.com/json.html


[PDL]: http://feeds.feedburner.com/PoorlyDrawnLines
