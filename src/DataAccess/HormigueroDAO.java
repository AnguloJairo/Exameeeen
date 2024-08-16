
// package DataAccess;

// import DataAccess.DTO.HormigueroDTO;
// import Framework.PatException;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;
// import java.util.List;

// public class HormigueroDAO extends SQLiteDataHelper implements IDAO<HormigueroDTO> {

//     @Override
//     public boolean create(HormigueroDTO entity) throws Exception {
//         String query = "INSERT INTO Hormiga (tipo_hormiga, sexo_id, provincia_id, geno_alimento_id, ingesta_nativa_id, EstadoHorm, Estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
//         try (Connection conn = openConnection(); 
//              PreparedStatement pstmt = conn.prepareStatement(query)) {
//             pstmt.setString(1, entity.getTipoHormiga());
//             pstmt.setInt(2, entity.getSexoId());
//             pstmt.setInt(3, entity.getProvinciaId());
//             pstmt.setInt(4, entity.getGenoAlimentoId());
//             pstmt.setInt(5, entity.getIngestaNativaId());
//             pstmt.setString(6, entity.getEstadoHorm());
//             pstmt.setString(7, entity.getEstado());
//             pstmt.executeUpdate();
//             return true;
//         } catch (SQLException e) {
//             throw new PatException(e.getMessage(), getClass().getName(), "create()");
//         }
//     }
//     @Override
// public List<HormigueroDTO> readAll() throws Exception {
//     List<HormigueroDTO> lst = new ArrayList<>();
//     String query = "SELECT ROW_NUMBER() OVER (ORDER BY id) RowNum, id, tipo_hormiga, sexo_id, provincia_id, geno_alimento_id, ingesta_nativa_id, EstadoHorm, Estado FROM Hormiga";
//     try (Connection conn = openConnection();
//          Statement stmt = conn.createStatement();
//          ResultSet rs = stmt.executeQuery(query)) {
        
//         while (rs.next()) {
//             HormigueroDTO dto = new HormigueroDTO();
//             dto.setRowNum(rs.getInt("RowNum"));
//             dto.setId(rs.getInt("id"));
//             dto.setTipoHormiga(rs.getString("tipo_hormiga"));
//             dto.setSexoId(rs.getInt("sexo_id"));
//             dto.setProvinciaId(rs.getInt("provincia_id"));
//             dto.setGenoAlimentoId(rs.getInt("geno_alimento_id"));
//             dto.setIngestaNativaId(rs.getInt("ingesta_nativa_id"));
//             dto.setEstadoHorm(rs.getString("EstadoHorm"));
//             dto.setEstado(rs.getString("Estado"));
//             lst.add(dto);
//         }
        
//         if (lst.isEmpty()) {
//             System.out.println("No se encontraron datos en la tabla Hormiga.");
//         } else {
//             System.out.println("Se cargaron " + lst.size() + " registros desde la tabla Hormiga.");
//         }
        
//     } catch (SQLException e) {
//         throw new PatException(e.getMessage(), getClass().getName(), "readAll()");
//     }
//     return lst;
// }


//     @Override
//     public boolean update(HormigueroDTO entity) throws Exception {
//         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//         LocalDateTime now = LocalDateTime.now();
//         String query = "UPDATE Hormiga SET tipo_hormiga = ?, sexo_id = ?, provincia_id = ?, geno_alimento_id = ?, ingesta_nativa_id = ?, EstadoHorm = ?, Estado = ?, FechaModifica = ? WHERE id = ?";
//         try (Connection conn = openConnection(); 
//              PreparedStatement pstmt = conn.prepareStatement(query)) {
//             pstmt.setString(1, entity.getTipoHormiga());
//             pstmt.setInt(2, entity.getSexoId());
//             pstmt.setInt(3, entity.getProvinciaId());
//             pstmt.setInt(4, entity.getGenoAlimentoId());
//             pstmt.setInt(5, entity.getIngestaNativaId());
//             pstmt.setString(6, entity.getEstadoHorm());
//             pstmt.setString(7, entity.getEstado());
//             pstmt.setString(8, dtf.format(now));
//             pstmt.setInt(9, entity.getId());
//             pstmt.executeUpdate();
//             return true;
//         } catch (SQLException e) {
//             throw new PatException(e.getMessage(), getClass().getName(), "update()");
//         }
//     }

// //     @Override
// // public boolean delete(int id) throws Exception {
// //     String query = "DELETE FROM Hormiga WHERE id = ?";
// //     try (Connection conn = openConnection(); 
// //          PreparedStatement pstmt = conn.prepareStatement(query)) {
// //         pstmt.setInt(1, id);
// //         int rowsAffected = pstmt.executeUpdate();
// //         return rowsAffected > 0;  // Retorna true si se eliminó al menos un registro
// //     } catch (SQLException e) {
// //         throw new PatException(e.getMessage(), getClass().getName(), "delete()");
// //     }
// // }
// @Override
// public boolean delete(int id) throws Exception {
//     String query = "DELETE FROM Hormiga WHERE id = ?";
//     try (Connection conn = openConnection();
//          PreparedStatement pstmt = conn.prepareStatement(query)) {
//         pstmt.setInt(1, id);
//         int affectedRows = pstmt.executeUpdate();
//         return affectedRows > 0;
//     } catch (SQLException e) {
//         throw new PatException(e.getMessage(), getClass().getName(), "delete()");
//     }
// }



    



//     @Override
//     public HormigueroDTO readBy(Integer id) throws Exception {
//         HormigueroDTO s = new HormigueroDTO();
//         String query = "SELECT  ROW_NUMBER () OVER (ORDER BY id) RowNum, id, tipo_hormiga, sexo_id, provincia_id, geno_alimento_id, ingesta_nativa_id, EstadoHorm, Estado, FechaCrea, FechaModifica FROM Hormiga WHERE id = ?";
//         try (Connection conn = openConnection(); 
//              PreparedStatement pstmt = conn.prepareStatement(query)) {
//             pstmt.setInt(1, id);
//             try (ResultSet rs = pstmt.executeQuery()) {
//                 if (rs.next()) {
//                     s = new HormigueroDTO(
//                         rs.getInt("RowNum"),
//                         rs.getInt("id"),
//                         rs.getString("tipo_hormiga"),
//                         rs.getInt("sexo_id"),
//                         rs.getInt("provincia_id"),
//                         rs.getInt("geno_alimento_id"),
//                         rs.getInt("ingesta_nativa_id"),
//                         rs.getString("EstadoHorm"),
//                         rs.getString("Estado"),
//                         rs.getString("FechaCrea"),
//                         rs.getString("FechaModifica")
//                     );
//                 }
//             }
//         } catch (SQLException e) {
//             throw new PatException(e.getMessage(), getClass().getName(), "readBy()");
//         }
//         return s;
//     }

//     //@Override
//     public Integer getRowCount() throws Exception {
//         String query = "SELECT COUNT(*) FROM Hormiga WHERE Estado = 'A'";
//         try (Connection conn = openConnection(); 
//              Statement stmt = conn.createStatement(); 
//              ResultSet rs = stmt.executeQuery(query)) {
//             if (rs.next()) {
//                 return rs.getInt(1);  // TotalReg
//             }
//         } catch (SQLException e) {
//             throw new PatException(e.getMessage(), getClass().getName(), "getRowCount()");
//         }
//         return 0;
//     }
// }


package DataAccess;

import DataAccess.DTO.HormigueroDTO;
import Framework.PatException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HormigueroDAO extends SQLiteDataHelper implements IDAO<HormigueroDTO> {

    @Override
    public boolean create(HormigueroDTO entity) throws Exception {
        String query = "INSERT INTO Hormiga (tipo_hormiga, sexo_id, provincia_id, geno_alimento_id, ingesta_nativa_id, EstadoHorm, Estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = openConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, entity.getTipoHormiga());
            pstmt.setInt(2, entity.getSexoId());
            pstmt.setInt(3, entity.getProvinciaId());
            pstmt.setInt(4, entity.getGenoAlimentoId());
            pstmt.setInt(5, entity.getIngestaNativaId());
            pstmt.setString(6, entity.getEstadoHorm());
            pstmt.setString(7, entity.getEstado());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "create()");
        }
    }
    @Override
public List<HormigueroDTO> readAll() throws Exception {
    List<HormigueroDTO> lst = new ArrayList<>();
    String query = "SELECT ROW_NUMBER() OVER (ORDER BY id) RowNum, id, tipo_hormiga, sexo_id, provincia_id, geno_alimento_id, ingesta_nativa_id, EstadoHorm, Estado FROM Hormiga";
    try (Connection conn = openConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        
        while (rs.next()) {
            HormigueroDTO dto = new HormigueroDTO();
            dto.setRowNum(rs.getInt("RowNum"));
            dto.setId(rs.getInt("id"));
            dto.setTipoHormiga(rs.getString("tipo_hormiga"));
            dto.setSexoId(rs.getInt("sexo_id"));
            dto.setProvinciaId(rs.getInt("provincia_id"));
            dto.setGenoAlimentoId(rs.getInt("geno_alimento_id"));
            dto.setIngestaNativaId(rs.getInt("ingesta_nativa_id"));
            dto.setEstadoHorm(rs.getString("EstadoHorm"));
            dto.setEstado(rs.getString("Estado"));
            lst.add(dto);
        }
        
        if (lst.isEmpty()) {
            System.out.println("No se encontraron datos en la tabla Hormiga.");
        } else {
            System.out.println("Se cargaron " + lst.size() + " registros desde la tabla Hormiga.");
        }
        
    } catch (SQLException e) {
        throw new PatException(e.getMessage(), getClass().getName(), "readAll()");
    }
    return lst;
}


    @Override
    public boolean update(HormigueroDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Hormiga SET tipo_hormiga = ?, sexo_id = ?, provincia_id = ?, geno_alimento_id = ?, ingesta_nativa_id = ?, EstadoHorm = ?, Estado = ?, FechaModifica = ? WHERE id = ?";
        try (Connection conn = openConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, entity.getTipoHormiga());
            pstmt.setInt(2, entity.getSexoId());
            pstmt.setInt(3, entity.getProvinciaId());
            pstmt.setInt(4, entity.getGenoAlimentoId());
            pstmt.setInt(5, entity.getIngestaNativaId());
            pstmt.setString(6, entity.getEstadoHorm());
            pstmt.setString(7, entity.getEstado());
            pstmt.setString(8, dtf.format(now));
            pstmt.setInt(9, entity.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
public boolean delete(int id) throws Exception {
    String query = "DELETE FROM Hormiga WHERE id = ?";
    try (Connection conn = openConnection(); 
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, id);
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;  // Retorna true si se eliminó al menos un registro
    } catch (SQLException e) {
        throw new PatException(e.getMessage(), getClass().getName(), "delete()");
    }
}

    



    @Override
    public HormigueroDTO readBy(Integer id) throws Exception {
        HormigueroDTO s = new HormigueroDTO();
        String query = "SELECT  ROW_NUMBER () OVER (ORDER BY id) RowNum, id, tipo_hormiga, sexo_id, provincia_id, geno_alimento_id, ingesta_nativa_id, EstadoHorm, Estado, FechaCrea, FechaModifica FROM Hormiga WHERE id = ?";
        try (Connection conn = openConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    s = new HormigueroDTO(
                        rs.getInt("RowNum"),
                        rs.getInt("id"),
                        rs.getString("tipo_hormiga"),
                        rs.getInt("sexo_id"),
                        rs.getInt("provincia_id"),
                        rs.getInt("geno_alimento_id"),
                        rs.getInt("ingesta_nativa_id"),
                        rs.getString("EstadoHorm"),
                        rs.getString("Estado"),
                        rs.getString("FechaCrea"),
                        rs.getString("FechaModifica")
                    );
                }
            }
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return s;
    }

    //@Override
    public Integer getRowCount() throws Exception {
        String query = "SELECT COUNT(*) FROM Hormiga WHERE Estado = 'A'";
        try (Connection conn = openConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);  // TotalReg
            }
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "getRowCount()");
        }
        return 0;
    }
}



