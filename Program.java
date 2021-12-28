package tugasP14;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Program extends Transaksi{

    static Connection conn;
    public static void main(String[] args) throws Exception {
        
        Scanner terimaInput = new Scanner (System.in);
		String pilihanUser;
		boolean isLanjutkan = true;
        String url = "jdbc:mysql://localhost:3306/tugasp14";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","");
			System.out.println("Class Driver ditemukan");
			
			while (isLanjutkan) {
				System.out.println("\n=====================");
				System.out.println("Database Minimarket");
				System.out.println("=====================");
				System.out.println("1. Lihat Data Transaksi");
				System.out.println("2. Tambah Data Transaksi");
				System.out.println("3. Ubah Data Transaksi");
				System.out.println("4. Hapus Data Transaksi");
				System.out.println("5. Cari Data Transaksi");
				
				System.out.print("\nPilihan (1-5): ");
				pilihanUser = terimaInput.next();
				
				switch (pilihanUser) {
				case "1":
					lihatdata();
					break;
				case "2":
					tambahdata();
					break;
				case "3":
					ubahdata();
					break;
				case "4":
					hapusdata();
					break;
				case "5":
					caridata();
					break;
				default:
					System.err.println("\nPilihan hanya 5");
				}
				
				System.out.print("\nApakah Anda ingin melanjutkan [y]? ");
				pilihanUser = terimaInput.next();
				isLanjutkan = pilihanUser.equalsIgnoreCase("y");
			}
			System.out.println("\nSelesai!");
        }
		catch(ClassNotFoundException ex) {
			System.err.println("Driver Error");
			System.exit(0);
		}
		catch(SQLException e){
			System.err.println("Tidak berhasil koneksi");
		}
    }

    private static void lihatdata() throws SQLException {
        String text1 = "\n===Seluruh Data Transaksi===";
        System.out.println(text1.toUpperCase());
                        
        String sql ="SELECT * FROM transaksi";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        
        while(result.next()){
            System.out.print("\nNo. Faktur\t: ");
            System.out.print(result.getString("no_faktur"));
            System.out.print("\nNama      \t: ");
            System.out.print(result.getString("nama"));
            System.out.print("\nTanggal\t: ");
            System.out.print(result.getString("tanggal"));
            System.out.print("\nNama Barang\t: ");
            System.out.print(result.getString("nama_barang"));
            System.out.print("\nHarga Barang\t: ");
            System.out.print(result.getLong("harga_barang"));
            System.out.print("\nJumlah\t\t: ");
            System.out.print(result.getLong("jumlah"));
            System.out.print("\nSub Total\t: ");
            System.out.print(result.getLong("subtotal"));
            System.out.print("\nDiscount\t: ");
            System.out.print(result.getLong("discount"));
            System.out.print("\nTotal Harga\t: ");
            System.out.print(result.getLong("total_harga"));
            System.out.print("\n");
        }
    }

    private static void tambahdata() throws SQLException{
		String text2 = "\n===Tambah Data Transaksi===";
		System.out.println(text2.toUpperCase());
				
		try {

        Transaksi pembelian1 = new Transaksi();
        pembelian1.toUpperCase();
        pembelian1.NamaBarang();
        pembelian1.NoFaktur();
        pembelian1.HargaBarang();
        pembelian1.Jumlah();
        System.out.println("========================================================");
        pembelian1.tampil();
        pembelian1.SubTotal();
        pembelian1.Discount();
        pembelian1.TotalHarga();
		
		String sql = "INSERT INTO transaksi (no_faktur,nama, tanggal, nama_barang, harga_barang, jumlah, subtotal, discount, total_harga) VALUES ('"+pembelian1.noFaktur+"','"+pembelian1.nama+"','"+pembelian1.date+"','"+pembelian1.namaBarang+"','"+pembelian1.hargaBarang+"','"+pembelian1.jumlah+"','"+pembelian1.subTotal+"','"+pembelian1.discount+"','"+pembelian1.totalHarga+"')";
					
        Statement statement = conn.createStatement();
        statement.execute(sql);
        System.out.println("Berhasil input data");
	
	    } catch (SQLException e) {
	        System.err.println("Terjadi kesalahan input data");
	    } catch (InputMismatchException e) {
	    	System.err.println("Masukkan inputan yang valid");
	   	}
	}

    private static void ubahdata() throws SQLException{
		String text3 = "\n===Ubah Data Transaksi===";
		System.out.println(text3.toUpperCase());
		
		Scanner terimaInput = new Scanner (System.in);
		
		try {
            lihatdata();
            System.out.print("Masukkan No. Faktur Transaksi yang akan diubah : ");
            String noFaktur = terimaInput.nextLine();
            
            String sql = "SELECT * FROM transaksi WHERE no_faktur = " +noFaktur;
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next()){
                              
                System.out.print("Nama ["+result.getString("nama")+"]\t: ");
                String nama = terimaInput.nextLine();


                sql = "UPDATE transaksi SET nama = '"+nama+"' WHERE no_faktur='"+noFaktur+"'";

                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data Transaksi (No. Faktur "+noFaktur+")");
                }
            }
            statement.close();        
        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
	}

    private static void hapusdata() {
		String text4 = "\n===Hapus Data Transaksi===";
		System.out.println(text4.toUpperCase());
		
		Scanner terimaInput = new Scanner (System.in);
		
		try{
	        lihatdata();
	        System.out.print("Ketik No. Faktur Transaksi yang akan Anda Hapus : ");
	        String noFaktur= terimaInput.nextLine();
	        
	        String sql = "DELETE FROM transaksi WHERE no_faktur = "+ noFaktur;
	        Statement statement = conn.createStatement();
	        //ResultSet result = statement.executeQuery(sql);
	        
	        if(statement.executeUpdate(sql) > 0){
	            System.out.println("Berhasil menghapus data Transaksi (No. Faktur "+noFaktur+")");
	        }
	   }catch(SQLException e){
	        System.out.println("Terjadi kesalahan dalam menghapus data transaksi");
	        }
	}

    private static void caridata () throws SQLException {
		String text5 = "\n===Cari Data Transaksi===";
		System.out.println(text5.toUpperCase());
		
		Scanner terimaInput = new Scanner (System.in);
				
		System.out.print("Masukkan Nama Barang : ");
        
		String keyword = terimaInput.nextLine();
        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM transaksi WHERE nama_barang LIKE '%"+keyword+"%'";
        ResultSet result = statement.executeQuery(sql); 
                
        while(result.next()){
        	System.out.print("\nNo. Faktur\t: ");
            System.out.print(result.getString("no_faktur"));
            System.out.print("\nTanggal\t: ");
            System.out.print(result.getString("tanggal"));
            System.out.print("\nNama Barang\t: ");
            System.out.print(result.getString("nama_barang"));
            System.out.print("\nHarga Barang\t: ");
            System.out.print(result.getInt("harga_barang"));
            System.out.print("\nJumlah\t: ");
            System.out.print(result.getString("jumlah"));
            System.out.print("\nSub Total\t: ");
            System.out.print(result.getString("subtotal"));
            System.out.print("\nDiscount\t: ");
            System.out.print(result.getInt("discount"));
            System.out.print("\nTotal Harga\t: ");
            System.out.print(result.getString("total_harga"));
            System.out.print("\n");
        }
	}
    
}