package racingcar

import camp.nextstep.edu.missionutils.Randoms

// Car 데이터 클래스 정의
data class Car(val name: String, var count: Int)

val carNames = mutableListOf<Car>() // Car 객체를 저장하는 리스트

fun checkInputIsNull(input: String?){
    if (input.isNullOrEmpty()){
        throw IllegalArgumentException("입력값이 없거나 공백입니다.")
    }
}

fun moveOrStop(index: Int){
    val randomNumber = Randoms.pickNumberInRange(0, 9)
    if(randomNumber >= 4){
        carNames[index].count +=1
    }
    print("${carNames[index].name} : ${"-".repeat(carNames[index].count)}\n")
}

fun playByCarSize(){
    for (j in 0 until carNames.size) {
        moveOrStop(j)
    }
    print("\n")
}


// 최대 'count' 값을 가지는 Car 객체의 이름 찾기
fun findNameOfWinnerWithMaxCount(): List<String> {
    val maxCount = carNames.maxByOrNull { it.count }?.count
    val winners = carNames.filter { it.count == maxCount }
    return winners.map { it.name }
}


fun main() {
    print("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)\n")
    val input = readLine()

    checkInputIsNull(input)

    input?.let { nonNullInput ->
        carNames.addAll(nonNullInput.split(",").map { Car(it.trim(), 0) }) // Car 객체로 저장
    }

    val invalidCarNames = carNames.filter { it.name.length > 5 }
    if (invalidCarNames.isNotEmpty()){
        throw IllegalArgumentException("자동차의 입력값의 길이는 5 이하로 입력해주세요.")
    }

    print("시도할 횟수는 몇 회인가요?\n")
    val playTime = readLine()

    checkInputIsNull(playTime)
    val playTimeAsInt: Int

    try {
        playTimeAsInt = playTime!!.toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("유효한 정수를 입력해주세요.")
    }

    print("\n실행결과\n")

    // 경주 게임 실행
    for (i in 1..playTimeAsInt) {
        playByCarSize()
    }

    val winnerNames = findNameOfWinnerWithMaxCount()
    print("최종 우승자 : ${winnerNames.joinToString(", ")}")

}
