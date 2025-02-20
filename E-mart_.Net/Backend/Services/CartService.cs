using E_mart.Models;
using E_mart.Repositories;
using Microsoft.EntityFrameworkCore;

namespace E_mart.Services
{
    public class CartService : ICartService
    {
        private readonly eMartDbContext _context;

        public CartService(eMartDbContext context)
        {
            _context = context;
        }

        public async Task<string> AddToCartAsync(Cart cart)
        {
            var user = await _context.Users.FindAsync(cart.Userid);
            var product = await _context.Products.FindAsync(cart.Productid);

            if (user == null || product == null)
            {
                return "Invalid user or product!";
            }

            var existingCartItems = await _context.Carts
                .Where(c => c.Userid == cart.Userid && c.Productid == cart.Productid)
                .ToListAsync();

            if (existingCartItems.Any())
            {
                return "Product is already in the cart!";
            }

            cart.User = null;  // Prevent serialization error
            cart.Product = null;
            cart.CreatedAt = DateTime.UtcNow;
            cart.UpdatedAt = DateTime.UtcNow;

            await _context.Carts.AddAsync(cart);
            await _context.SaveChangesAsync();
            return "Product added to cart!";
        }

        public async Task<string> RemoveFromCartAsync(int cartId)
        {
            var cartItem = await _context.Carts.FindAsync(cartId);
            if (cartItem != null)
            {
                _context.Carts.Remove(cartItem);
                await _context.SaveChangesAsync();
                return "Item removed from cart successfully!";
            }
            return "Cart item not found!";
        }

        public async Task<string> UpdateCartItemAsync(int cartId, int quantity)
        {
            var cartItem = await _context.Carts.FindAsync(cartId);
            if (cartItem != null)
            {
                cartItem.Quantity = quantity;
                cartItem.UpdatedAt = DateTime.UtcNow;
                _context.Carts.Update(cartItem);
                await _context.SaveChangesAsync();
                return "Cart item updated successfully!";
            }
            return "Cart item not found!";
        }

        public async Task<List<Cart>> ViewCartAsync(int userId)
        {
            return await _context.Carts
                .Include(c => c.Product)
                .Where(c => c.Userid == userId)
                .ToListAsync();
        }
    }
}