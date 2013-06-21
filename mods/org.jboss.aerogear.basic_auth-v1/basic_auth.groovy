import java.util.logging.Logger

def eb = vertx.eventBus

eb.registerHandler(".login", { message -> 
    def reply = [:]
    reply['headers'] = [:]
    try {
        Logger logger = Logger.getLogger("")
        logger.info (message.body.toString())
        if (message.body['headers']['authorization'] == 'Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==') {
            reply['statusCode'] = 200 ;
        } else {
            reply['statusCode'] = 401 ;
        }
        reply.body = "{'\n'}"
    } finally {
        message.reply reply
    }
}, {eb.send('test-registered', '.login')}
)



