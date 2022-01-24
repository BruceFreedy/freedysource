@command
{
    # args = message.split=' '
    if(!message.toLowerCase.startWith="/mgstart") return
    player.print='pre'
    return true;
    player.print='sff'
    player.print='debug'
}