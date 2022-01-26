대기 = "대기"
플레이 = "플레이"
종료 = "종료"

시작전인벤토리 = "시작전인벤토리"
팀 = "팀"
빨강 = "빨강"
파랑 = "파랑"

게임이름 = "좆게임"

online = []
상태 = 대기


시작인원 = 2
시작타이머 = 5

파랑팀시작위치 = location("world", 0, 0, 0)
빨강팀시작위치 = location("world", 0, 0, 0)


@command
{
    args = message.split(" ")                    # 명령어를 공백을 기준으 로 나눕니다
    command = args.get(0)
    if (command.lowerCase != "/gamestart") return  # 명령어가 게임시작이 아니면 함수를 즉시 끝냅니다
    player.print=online
    player.print=online.contains=player
    if (online.contains(player)) return player.print = "&c이미 게임에 참여하고 있습니다"
    player.print="&a" + 상태
    if (상태 != 대기) return player.print = "&c이미 게임이 시작되서 참여할 수 없습니다"
    online.add(player)
    online.each=print ( "&6" + player.name + "이(가) " + 게임이름 + "에 참여했습니다" )
    if(online.size >= 시작인원) {
        상태 = 플레이
        start()
    }
}

