def eb = vertx.eventBus

eb.registerHandler(".test.post", { message -> 
    def reply = [:]
    reply['headers'] = [:]
    try {
        
        reply.body = "{'authorUsername':'noname'}"
    } finally {
        message.reply reply
    }
}, {eb.send('test-registered', '.test.post')}
)




