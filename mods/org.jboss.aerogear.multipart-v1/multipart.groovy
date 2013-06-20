import org.vertx.groovy.core.http.RouteMatcher

def server = vertx.createHttpServer()

def rm = new RouteMatcher()

rm.post('/test/post') { req ->
  req.response.with {
      end "{'authorUsername':'noname'}"
      close()
  }
     
}

vertx.createHttpServer().requestHandler(rm.asClosure()).listen(8080)
