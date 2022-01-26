online = []
@join online.add(player)
@left online.remove(player)

joined = []

@command
{
    player.print("[" + message + "]")
    player.print(message.split(" "))
    args = message.split(" ")
    player.print(args.get(0).lowerCase)
}