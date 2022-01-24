@command
{
    before = millisec
    args = message.split=' '
    after = millisec
    player.print=after-before
}