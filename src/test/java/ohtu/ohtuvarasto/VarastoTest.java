package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaLiikaa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(10);
        
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLaittaaLiikaa() {
        varasto.lisaaVarastoon(12);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaAlleNolla() {
        varasto.lisaaVarastoon(2);
        double allenolla = varasto.otaVarastosta(-1);
        
        assertEquals(0, allenolla, vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisataAlleNolla() {
        varasto.lisaaVarastoon(-2);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoEiVoiOllaMiinuksella() {
        Varasto varasto2 = new Varasto(-1);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoEiVoiOllaMiinuksella2() {
        Varasto varasto2 = new Varasto(-1, 0);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoJaSaldoKoko() {
        Varasto varasto2 = new Varasto(2,0);
        assertEquals(2, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoJaSaldoSaldo() {
        Varasto varasto2 = new Varasto(2,2);
        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoEiVoiOllaMiinuksella() {
        Varasto varasto2 = new Varasto(1,-1);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoaEiVoiOllaEnemmanKuinTilaaAlussa() {
        Varasto varasto2 = new Varasto(10,12);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void stringOnOikein() {
        Varasto varasto2 = new Varasto(10,0);
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto2.toString());
    }

}