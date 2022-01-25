name = "테투"  //게임 세션 이름  ex) CashmereCat9의 게임
status = "대기"  //게임 상태 { 대기, 카운트다운, 플레이, 종료 }
online = []  //게임에 참여 중인 플레이어 목록
gameStartAmount = 2  //게임 시작 인원
countDownSize = 5  //게임 카운트 다운 시간
countDownTask = null  //게임 카운트 다운 스케줄 작업 ID
/**
 * real is PlayerCommandPreprocessEvent for Bukkit
 */
@command
{
        if (!(message.toLowerCase.startWith("/gamestart"))) return  //명령어가 대소문자 구분없이 /gamestart 로 시작하는가
        if (online.contains(player)) return player.print="이미 그 미니게임에 있습니다"
        if (status != "대기") return player.print='게임이 이미 시작해서 참여할 수 없습니다'
        online.add(player)
        online.each=e.print('&6' + player.name + '이(가) ' + name + ' 게임에 참여했습니다')
        if (online.size >= gameStartAmount) {
                status = "카운트다운"
                online.each=e.print("게임이 " + countDownSize + "초후에 시작됩니다")
                countDownTask = delay countDownSize, start()
        } else player.print('&6미니게임 시작까지 ' + (gameStartAmount - online.size) + '명이 더 필요합니다')
}

@left left(player)
left(player) {
        if (!online.contains(player)) return
        online.remove(player)
        online.each=e.print("&a" + player.name + "이(가)" + name + " 게임을 떠났습니다")
        if (countDownTask > 0) {
                online.each=e.print="&c게임 인원이 충분하지 않습니다"
                canceltask(countDownTask)
                countDownTask = null
        }
}

start() {
        online.each=e.prin='&a게임이 시작되었습니다'

}