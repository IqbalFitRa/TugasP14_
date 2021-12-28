package tugasP14;

import java.util.Scanner;

public class Barang implements Penjualan
{
    Long noFaktur;
    String namaBarang;
    Long hargaBarang;
    Long jumlah;

    Scanner a = new Scanner(System.in);
    
    @Override
    public void NoFaktur()
    {
        System.out.print(" Input Nomor Faktur           : ");
        noFaktur = a.nextLong();
    }

    @Override
    public void NamaBarang()
    {
        System.out.print(" Input Nama Barang            : ");
        namaBarang = a.nextLine();
    }

    @Override
    public void HargaBarang()
    {
        System.out.print(" Input Harga Barang           : ");
        hargaBarang = a.nextLong();
    }

    @Override
    public void Jumlah()
    {
        System.out.print(" Input Jumlah Barang          : ");
        jumlah = a.nextLong();
    }
    
    @Override
    public void SubTotal()
    {
        System.out.println(" kosong");
    }
    
    @Override
    public void Discount()
    {
        System.out.println(" kosong");
    }

    @Override
    public void TotalHarga()
    {
        System.out.println(" kosong");
    }
}
