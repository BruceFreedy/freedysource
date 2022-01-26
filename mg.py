online = []
상태 = 대기
게임이름 = "좆게임"

대기 = "대기"
플레이 = "플레이"
종료 = "종료"

시작인원 = 2
시작타이머 = 5

파랑팀시작위치 = location("world", 0, 0, 0)
빨강팀시작위치 = location("world", 0, 0, 0)

시작전인벤토리 = "시작전인벤토리"
팀 = "팀"
빨강 = "빨강"
파랑 = "파랑"

@command
{
    args = message.split(" ")                    # 명령어를 공백을 기준으로 나눕니다
    command = args.get(0)
    if (command.lowerCase != "/gamestart") return  # 명령어가 게임시작이 아니면 함수를 즉시 끝냅니다
    if (online.contains(player)) return player.print = "&c이미 게임에 참여하고 있습니다"
    if (상태 != 대기) return player.print = "&c이미 게임이 시작되서 참여할 수 없습니다"
    online.add(player)
    online.each=e.player.print="&6" + player.name + "이(가) " + 게임이름 + "에 참여했습니다"
    if(online.size >= 시작인원) {
        상태 = 플레이
        start()
    }
}

start() {
    online.each=e.print="&6" + 시작타이머 + "초 후에 " + 게임이름 + " 게임이 시작됩니다"
    delay 시작타이머 * 20, {
        for(i = 0; i < online.size; i++)
        {
            p = online.get(i)
            p.set(팀, {if(i%2==0) return 빨강 else return 파랑})
        }
    }
}