package app.sport.sw.enums.region;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static app.sport.sw.enums.region.ParentRegion.*;

@Getter
@AllArgsConstructor
public enum Region {

    // 도쿄도
    ADACHI(TOKYO),
    ARAKAWA(TOKYO),
    ITABASHI(TOKYO),
    EDOGAWA(TOKYO),
    OTA(TOKYO),
    KATSUSHIKA(TOKYO),
    KITA(TOKYO),
    KOTO(TOKYO),
    SHINAGAWA(TOKYO),
    SHIBUYA(TOKYO),
    SHINJUKU(TOKYO),
    SUGINAMI(TOKYO),
    SUMIDA(TOKYO),
    SETAGAYA(TOKYO),
    TAITO(TOKYO),
    CHUO(TOKYO),
    CHIYODA(TOKYO),
    TOSHIMA(TOKYO),
    NAKANO(TOKYO),
    NERIMA(TOKYO),
    BUNKYO(TOKYO),
    MINATO(TOKYO),
    MEGURO(TOKYO);


    private final ParentRegion parentRegion;

}
