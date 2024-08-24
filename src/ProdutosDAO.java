import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto (ProdutosDTO produto){

        conn = new conectaDAO().connectDB();

        String sql = "INSERT INTO PRODUTOS (nome,valor,status) VALUES (?,?,?)";

        try {
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar " + e.getMessage());

        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos() {
        
        return listagem;
    }
}
