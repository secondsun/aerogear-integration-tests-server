import org.vertx.groovy.core.http.RouteMatcher

def rm = vertx.rm

rm.post('/test/post') { req ->
  req.response.with {
      end "{'authorUsername':'noname'}"
      close()
  }
     
}


