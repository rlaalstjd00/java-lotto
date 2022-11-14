package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Collections;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
    }

    // 로또번호 자동생성 후 List 타입으로 반환
    public List<Integer> createLottoNumbers(){
        List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(1,45,6);

        return lottoNumbers;
    }

    // 로또 번호 List 오름차순으로 정렬
    public List<Integer> sortLottoNumbers(List<Integer> lottoNumbers){
        Collections.sort(lottoNumbers);

        return lottoNumbers;
    }
}
