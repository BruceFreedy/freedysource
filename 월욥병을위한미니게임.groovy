//////////////////////////////////////////////////////////
대기 = "대기"
플레이 = "플레이"
종료 = "종료"
//////////////////////////////////////////////////////////
시작전인벤토리 = "시작전인벤토리"
팀 = "팀"
빨강 = "빨강"
파랑 = "파랑"
//////////////////////////////////////////////////////////
게임이름 = "f.u.c.k.y.o.u"
//////////////////////////////////////////////////////////
시작인원 = 1
시작타이머 = 5
시작타이머작업 = 0
파랑팀시작위치 = location("world", 0, 0, 0)
빨강팀시작위치 = location("world", 0, 0, 0)
//////////////////////////////////////////////////////////
참여자 = []
상태 = 대기
//////////////////////////////////////////////////////////

@command
{
    args = message.split(" ")  //명령어를 공백을 기준으 로 나눕니다
    command = args.get(0)

    if (command.lowerCase != "/gamestart") return  //명령어가 게임시작이 아니면 함수를 즉시 끝냅니다

    if (참여자.contains(player)) return player.print = "&c이미 게임에 참여하고 있습니다"

    if (상태 != 대기) return player.print = "&c이미 게임이 시작되서 참여할 수 없습니다"

    참여자.add=player
    참여자.each=print ( "&6" + player.name + "이(가) " + 게임이름 + "에 참여했습니다" )

    if(참여자.size >= 시작인원) {
        상태 = 플레이
        시작타이머작업 = delay 시작타이머 * 20, start
    }
}

@command if (message.split(" ").get(0).lowerCase=="/gameleft") left(player)
@left /*서버퇴장*/ left(player)

left(player) {
    if (참여자.contains(player)) {
        참여자.each=print="&c" + player.name + "이(가) " + 게임이름 + "을 떠났습니다"
        참여자.remove(player)
        if (player.get(시작전인벤토리) != null)
            player.inventory.contents = player.get(시작전인벤토리)
        if (참여자.size < 시작인원 && 시작타이머작업 != 0) {
            canceltask 시작타이머작업
            시작타이머작업 = 0
            참여자.each=print="&c플레이어가 충분하지 않아서 시작 타이머를 중지합니다"
        }
    }
}

start() {
    참여자.each=print="&a" + 게임이름 + "이(가) 시작되었습니다"
    참여자.each={
        set(시작전인벤토리, inventory.contents)
        inventory.clear
        inventory.get(0).type=material DIRT
    }
}