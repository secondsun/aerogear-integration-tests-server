import java.util.logging.Logger

def eb = vertx.eventBus

eb.registerHandler(".digest", { message -> 
    def reply = [:]
    reply['headers'] = [:]
    try {
        Logger logger = Logger.getLogger("")
        logger.info (message.body.toString())
        if (message.body['headers']['authorization'] == 'Digest username="Mufasa", ' +
                                    'realm="testrealm@host.com", ' +
                                    'nonce="dcd98b7102dd2f0e8b11d0f600bfb0c093", ' + 
                                    'uri="/dir/index.html", ' +
                                    'qop=auth, nc=00000001, ' + 
                                    'cnonce="0a4f113b", ' + 
                                    'response="6629fae49393a05397450978507c4ef1", ' + 
                                    'opaque="5ccc069c403ebaf9f0171e9517f40e41"') {
            reply['statusCode'] = 200 ;
            reply.body = "{'success'}"
        } else {
            reply['statusCode'] = 401 ;
            reply.headers['WWW-Authenticate'] = 'Digest realm="testrealm@host.com", ' +
                                                         'qop="auth,auth-int", ' + 
                                                         'nonce="dcd98b7102dd2f0e8b11d0f600bfb0c093", ' + 
                                                         'opaque="5ccc069c403ebaf9f0171e9517f40e41"'
        }
        
    } finally {
        message.reply reply
    }
}, {eb.send('test-registered', '.digest')}
)



