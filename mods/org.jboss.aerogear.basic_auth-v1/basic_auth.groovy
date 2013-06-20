import org.vertx.groovy.core.http.RouteMatcher

def server = vertx.createHttpServer()

def rm = new RouteMatcher()

rm.post('/login') { req ->
  if (req.headers['Authorization'] == 'Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==') {
    req.response.with { statusCode = 200 };
  } else {
    req.response.with { statusCode = 401 };
  }
  req.response.end "{'\n'}"
  req.response.close()
}

vertx.createHttpServer().requestHandler(rm.asClosure()).listen(8080)
