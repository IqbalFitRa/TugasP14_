package tugasP14;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaksi extends Barang

{
    Long subTotal;
    Long discount;
    Long totalHarga;
    String nama;
    char namaToko[] = {'A','P','A','-','A','P','A','L','A','H','_','S','H','O','P'};
    String Namatoko;
    String date;

    public void toUpperCase()
    {
        System.out.print(" Masukkan Nama Pembeli        : ");
        nama = a.nextLine();
        nama = nama.toUpperCase();
    }

    public void length ()
    {
        Integer panjangNama = nama.length();
        System.out.println(" Panjang Nama               : " +panjangNama+ " karakter");
    }

    public void copyValueOf()
    {
        Namatoko = Namatoko.copyValueOf(namaToko,0,15);
        System.out.println("                    " +Namatoko);
    }

    @Override
    public void SubTotal()
    {
        this.subTotal = this.hargaBarang * this.jumlah;
        System.out.println(" Jumlah Harga Awal          : " +subTotal);
    }

    @Override
    public void Discount()
    {
        if(subTotal > 100000 && subTotal <= 200000)
        {
            this.discount = subTotal*5/100;
        }
        else if (subTotal > 200000 && subTotal <= 400000)
        {
            this.discount = subTotal*7/100;
        }
        else if (subTotal > 400000 && subTotal <= 700000)
        {
            this.discount = subTotal*9/100;
        }
        else if (subTotal > 700000 && subTotal <= 1000000)
        {
            this.discount = subTotal*11/100;
        }
        else if(subTotal > 1000000)
        {
            this.discount = subTotal*13/100;
        }
        else
        {
            this.discount = (long) 0;
        }
        System.out.println(" Total Diskon yang Didapat  : " +discount);
    }

    @Override
    public void TotalHarga()
    {
        this.totalHarga = subTotal - discount;
        System.out.println(" Total Harga Pembayaran     : " +totalHarga);
    }

    public String dateTime()
    {
        Date tanggal = new Date();
        SimpleDateFormat b = new SimpleDateFormat("E yyyy.MM.dd 'pada' hh:mm:ss a ");
        date = b.format(tanggal);
        System.out.println(" Tanggal Transaksi          : " +b.format(tanggal));

        return date;
    }
    public void tampil()
    {   
        copyValueOf();
        System.out.println("========================================================");
        dateTime();
        System.out.println(" Nama Pembeli               : " +nama);
        length();
        System.out.println(" Nomor Faktur               : " +noFaktur);
        System.out.println(" Nama Barang                : " +namaBarang);
        System.out.println(" Harga Barang               : " +hargaBarang);
        System.out.println(" Jumlah Barang              : " +jumlah);
    }
}
