using E_mart.Models;

namespace E_mart.Services
{
    public interface IProductService
    {
        Task<IEnumerable<Product>> GetAllProducts();
        Task<Product> GetProductById(int id);

        Task<IEnumerable<Product>> GetProductsByCategory(int categoryid);

        Task<IEnumerable<Product>> GetProductsBySubcategory(int subcategoryid);
    }
}
