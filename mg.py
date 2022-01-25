online = []
@join online.add(player)
@left online.remove(player)

joined = []

@command
{
    args = message.split(" ")
    if(args.get(0).toCamelCase!="/gamestart") return
    joined.add=player
}