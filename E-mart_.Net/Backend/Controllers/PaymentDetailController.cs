using E_mart.Models;
using E_mart.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace E_mart.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    [Produces("application/json")]
    public class PaymentDetailsController : ControllerBase
    {
        // private readonly PaymentDetailsService _paymentService;
        private readonly IPaymentDetailService _paymentService;

        public PaymentDetailsController(IPaymentDetailService paymentService)
        {
            _paymentService = paymentService;
        }

        [HttpPost("process")]
        public async Task<IActionResult> ProcessPayment([FromBody] PaymentDetail payment)
        {
            if (payment == null)
                return BadRequest("Invalid payment details");

            var savedPayment = await _paymentService.ProcessPaymentAsync(payment);
            return Ok(savedPayment);
        }



        [HttpGet("status/{orderID}")]
        public async Task<IActionResult> GetPaymentStatus(int orderID)
        {
            var payment = await _paymentService.GetPaymentStatus(orderID);

            if (payment == null)
            {
                //Console.WriteLine("order is null");
                return NotFound();
            }

            return Ok(payment);
        }
    }
}
