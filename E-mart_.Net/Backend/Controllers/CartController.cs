using E_mart.Models;
using E_mart.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace E_mart.Controllers
{
    [Route("api/cart")]
    [ApiController]
    [EnableCors("AllowSpecificOrigin")]
    public class CartController : ControllerBase
    {
        private readonly ICartService _cartService;

        public CartController(ICartService cartService)
        {
            _cartService = cartService;
        }

        [HttpPost("add")]
        public async Task<IActionResult> AddToCart([FromBody] Cart cart)
        {
            if (cart == null)
                return BadRequest("Invalid cart data.");

            // Set default timestamp if missing
            cart.CreatedAt ??= DateTime.UtcNow;

            var result = await _cartService.AddToCartAsync(cart);
            if (result == "Product is already in the cart!")
                return BadRequest(result);

            return Ok(result);
        }

        [HttpDelete("remove/{cartId}")]
        public async Task<IActionResult> RemoveFromCart(int cartId)
        {
            var result = await _cartService.RemoveFromCartAsync(cartId);
            return Ok(result);
        }

        [HttpPut("update/{cartId}")]
        public async Task<IActionResult> UpdateCartItem(int cartId, [FromBody] int quantity)
        {
            var result = await _cartService.UpdateCartItemAsync(cartId, quantity);
            return Ok(result);
        }

        [HttpGet("view/{userId}")]
        

        public async Task<ActionResult<List<Cart>>> ViewCart(int userId)
        {
            var cartItems = await _cartService.ViewCartAsync(userId);
            return Ok(cartItems);
        }
    }
}