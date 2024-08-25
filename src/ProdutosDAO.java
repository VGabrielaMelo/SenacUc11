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

    public void cadastrarProduto (ProdutosDTO produto) {

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
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        conectaDAO conexao = new conectaDAO();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexao.connectDB();
            String sql = "SELECT * FROM produtos";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                listagem.add(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        
        return listagem;
    }

    public void venderProduto(int id) {
        conectaDAO conexao = new conectaDAO();
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Obtendo a conexão através da classe conectaDAO
            conn = conexao.connectDB();
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fechando os recursos
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
