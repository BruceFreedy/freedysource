online = []
@join online.add(player)
@left online.remove(player)

joined = []

@command
{
    player.print("[" + message + "]")
    args = message.split(" ")
    player.print(args)
    player.print=args.get(0).lowerCase
    if(args.get(0).lowerCase!="/gamestart") return





    joined.add=player
}