/*
    제작자: futureBase를 좋아하는 그 누군가, 즉 익명.
 */
게임이름 = "beta"

//  게임 상태 상수
대기 = "대기"
플레이 = "플레이"
종료 = "종료"

//  플레이어 데이타 키값
시작전인벤토리 = "시작전인벤토리"
팀 = "팀"
빨강 = "빨강"
파랑 = "파랑"
죽음 = "죽음"

//게임 초기화
시작인원 = 1
시작타이머 = 1
시작타이머작업 = 0
종료인원 = 1
종료타이머 = 3
종료타이머작업 = 0
파랑팀시작위치 = location("world", 0, 0, 0)
빨강팀시작위치 = location("world", 0, 0, 0)

//게임 변수
참여자 = []
상태 = 대기


@command
{
    args = message.split(" ")  //명령어를 공백을 기준으 로 나눕니다
    command = args.get(0)

    if (command.lowerCase != "/gamestart") return  //명령어가 게임시작이 아니면 함수를 즉시 끝냅니다

    cancelled = true

    if (참여자.contains(player)) {  //이미 게임에 참여하고 있으면
        return player.print = "&c이미 게임에 참여하고 있습니다"
    }
    if (상태 != 대기) {  //게임이 플레이 또는 종료 상태이면
        return player.print = "&c이미 게임이 시작되서 참여할 수 없습니다"
    }

    참여자.add=player  //참여자 목록에 플레이어를 추가
    say ( "&6" + player.name + "이(가) " + 게임이름 + "에 참여했습니다" )

    if(참여자.size >= 시작인원) {  //시작인원이 충분하면
        상태 = 플레이  //게임 상태를 플레이로 변경
        시작타이머작업 = delay(시작타이머 * 20) start  //시작타이머 발동
    }
}

@command
{
    if (message.split(" ").get(0).lowerCase == "/gameleft") {  //퇴장 명령어라면
        left(player, true); cancelled = true  //퇴장 하고 이벤트 취소
    }
}
@left left(player)  //서버 퇴장 시 미니게임 퇴장

left(player, send) {  //미니게임 퇴장
    if (참여자.contains(player)) {  //만약 미니게임에 참여하고 있다면
//        player.print("&7게임을 떠나는 중 입니다 ...")
        say = "&c" + player.name + "이(가) " + 게임이름 + "을(를) 떠났습니다"
        참여자.remove = player  //참여자 목록에서 플레이어 제거
        player.inventory.clear  //인벤토리 초기화
        if (player.get(시작전인벤토리) != null) {  //시작전 인벤토리값이 존재한다면
            player.inventory.contents = player.get(시작전인벤토리)  //시작 전 인벤토리로 설정
        }
        if (참여자.size < 시작인원 && 시작타이머작업 != 0) {  //시작 타이머가 작동 중이고 시작 인원이 부족하면
            canceltask 시작타이머작업  //시작타이머 취소
            시작타이머작업 = 0
            say = "&c플레이어가 충분하지 않아서 시작 타이머를 중지합니다"
        }
        if (참여자.size <= 종료인원 && 상태 == 플레이) {  //참여자수가 종료인원 이하라면 게임 종료
            delay(0) stop  //0틱 후에 게임 종료
        }
    } else if (send) player.print="&c참여 중인 게임이 없습니다"  //게임에 참여중이지 않으면

}

start() {  //게임 시작하기
    say ="&a" + 게임이름 + "이(가) 시작되었습니다"
    참여자.each={  //참여자 마다 실행
        set(시작전인벤토리, inventory.contents)  //현재 인벤토리를 플레이어 변수에 저장
        inventory.clear  //인벤토리 초기화
        inventory.set(0, item(material("IRON_SWORD")))  //0번 슬롯에 철 검 생성
        inventory.set(1, item(material("BREAD"), 64))  //1번 슬롯에 빵 64개 생성
    }
}

stop() {  //게임 종료하기
    상태 = 종료  //게임 상태를 종료로 설정
    say = "&c" + 게임이름 + "이(가) 종료되었습니다"
    승리= []  //승리한 플레이어 목록
    참여자.each={if(get(죽음)!=true) 승리.add=this}  //참여자중, 죽지 않은 플레이어를 승리자 목록에 추가
    say="&a승리 : " + 승리.sub(1, 승리.size - 1)  //승리자 목록 알림을 참여자에게 보냄
    종료타이머작업 = delay (종료타이머*20) {  //게임 비활성화 작업
        참여자.each=left(this)  //남은 참여자 모두 퇴장
        종료타이머작업=0
        상태=대기  //게임 상태를 대기로 설정
    }
}

say(msg) {참여자.each=print=msg}  //미니게임 참여자에게 알리기
