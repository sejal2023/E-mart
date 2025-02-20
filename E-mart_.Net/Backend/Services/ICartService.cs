using E_mart.Models;

namespace E_mart.Services
{
    public interface ICartService
    {

        Task<string> AddToCartAsync(Cart cart);
        Task<string> RemoveFromCartAsync(int cartId);
        Task<string> UpdateCartItemAsync(int cartId, int quantity);
        Task<List<Cart>> ViewCartAsync(int userId);

    }
}
