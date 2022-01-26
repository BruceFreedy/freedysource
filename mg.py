online = []
@join online.add(player)
@left online.remove(player)

joined = []

@command
{
    player.print("[" + message + "]")
    player.print(message.split(" "))
    player.print(message.split(" ").get(0).lowerCase)
}