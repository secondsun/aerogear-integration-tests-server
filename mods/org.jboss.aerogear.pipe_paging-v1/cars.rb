require "vertx"
require "java"
include Vertx

EventBus.register_handler('.cars') do |message|
puts "I received a message #{message.body}"

reply = Hash.new
reply["headers"] = Hash.new
reply["body"] = "This is a reply"

message.reply reply
end
 
EventBus.send('test-registered', '.cars') do |message|
puts "I received a reply #{message.body}"
end