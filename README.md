#### RSS Feed Reader

This application fetches the 10 latest topics from [https://xkcd.com/json.html][XKCD] and [http://feeds.feedburner.com/PoorlyDrawnLines][PDL] 
and combines them in a JSON format based on latest published date. The application uses REST Template to read data from XKCD and ROME api to fetch
PDL. They are composed in two Spring components and will be combined in FeedController. For convenience, Swagger UI has been set up.
The test coverage for the application is 83%. `getLatest` method of the `FeedController` has been tested.

The Dockerfile will build and run application. to build and run application with docker:

`docker build --build-arg JAR_FILE=target/*.jar -t rss-feed-reader .`
`docker run -p 8080:8080 rss-feed-reader`

`run.sh` and `run.cmd` will do it. 

Thanks for your review. 
 

[XKCD]: https://xkcd.com/json.html


[PDL]: http://feeds.feedburner.com/PoorlyDrawnLines
