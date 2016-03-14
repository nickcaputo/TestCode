/*
*  @author Nick Caputo
*
*  Based on the sample Node.js server in the Spotify Web API tutorial
*  https://developer.spotify.com/web-api/tutorial/
*/

/* Load the HTTP library */
var http = require("http");
var players = 0;
var server = http.createServer(choose);


function inter(request, response, toWrite) {
  response.writeHead(200, {"Content-Type": "text/plain"});
  response.write("Hello World, you are Nick");
  response.write('\n' + toWrite);
  response.end();
}

function inter2(request, response, toWrite) {
  response.writeHead(200, {"Content-Type": "text/plain"});
  response.write("Hello World, you are Chad");
  response.write('\n' + toWrite);
  response.end();
}

function choose(request, response) {
  if (request.url == '/') {
    players++;
    var toWrite = "This is player " + players;
    console.log(players);
    // console.log(request.url);
  
    if (players % 2 == 0) {
      inter(request, response, toWrite);
    } else {
      inter2(request, response, toWrite);
    }
  }
}

/* Create an HTTP server to handle responses */
server.listen(8080);
