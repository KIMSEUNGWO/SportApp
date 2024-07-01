package app.sport.sw.enums.region;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static app.sport.sw.enums.region.Location1.*;

@Getter
@AllArgsConstructor
public enum Location2 {

    // 도쿄도
    ADACHI(TOKYO, "아다치구", "Adachi", "足立区"),
    ARAKAWA(TOKYO, "아라카와구", "Arakawa", "荒川区"),
    ITABASHI(TOKYO, "이타바시구", "Itabashi", "板橋区"),
    EDOGAWA(TOKYO, "에도가와구", "Edogawa", "江戸川区"),
    OTA(TOKYO, "오타구", "Ota", "大田区"),
    KATSUSHIKA(TOKYO, "카츠시카구", "Katsushika", "葛飾区"),
    KITA(TOKYO, "키타구", "Kita", "北区"),
    KOTO(TOKYO, "고토구", "Koto", "江東区"),
    SHINAGAWA(TOKYO, "시나가와구", "Shinagawa", "品川区"),
    SHIBUYA(TOKYO, "시부야구", "Shibuya", "渋谷区"),
    SHINJUKU(TOKYO, "신주쿠구", "Shinjuku", "新宿区"),
    SUGINAMI(TOKYO, "스기나미구", "Suginami", "杉並区"),
    SUMIDA(TOKYO, "스미다구", "Sumida", "墨田区"),
    SETAGAYA(TOKYO, "세타가야구", "Setagaya", "世田谷区"),
    TAITO(TOKYO, "다이토구", "Taito", "台東区"),
    CHUO(TOKYO, "주오구", "Chuo", "中央区"),
    CHIYODA(TOKYO, "치요다구", "Chiyoda", "千代田区"),
    TOSHIMA(TOKYO, "도시마구", "Toshima", "豊島区"),
    NAKANO(TOKYO, "나카노구", "Nakano", "中野区"),
    NERIMA(TOKYO, "네리마구", "Nerima", "練馬区"),
    BUNKYO(TOKYO, "분쿄구", "Bunkyo", "文京区"),
    MINATO(TOKYO, "미나토구", "Minato", "港区"),
    MEGURO(TOKYO, "메구로구", "Meguro", "目黒区");


    private final Location1 parentLocation;
    private final String ko;
    private final String en;
    private final String jp;

}
