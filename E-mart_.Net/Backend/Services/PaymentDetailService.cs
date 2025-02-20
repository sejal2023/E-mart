using E_mart.Models;
using E_mart.Repositories;
using Microsoft.EntityFrameworkCore;

namespace E_mart.Services
{
    public class PaymentDetailService : IPaymentDetailService
    {
        private readonly eMartDbContext _dbContext;
        public PaymentDetailService(eMartDbContext dbcontext)
        {
            _dbContext = dbcontext;


        }

        public async Task<PaymentDetail> GetPaymentStatus(int OrderId)
        {
            //return await Task.FromResult<PaymentDetail>(null);
            return await _dbContext.PaymentDetails
        .FirstOrDefaultAsync(p => p.Orderid == OrderId);
        }

        //    public async Task<PaymentDetail> GetPaymentDetailAsync(int OrderId)
        //    {
        //        return await _dbContext.PaymentDetails
        //.FirstOrDefaultAsync(p => p.Orderid == OrderId);
        //    }

        public async Task<PaymentDetail> ProcessPaymentAsync(PaymentDetail payment)
        {
            //if (payment.Order == null)
            //{
            //    throw new ArgumentException("Order is not set in the PaymentDetails object");
            //}
            //var order = await _dbContext.Orders.FindAsync(payment.Orderid);
            //if (order == null)
            //{
            //    throw new ArgumentException($"Order not found with ID: {payment.Orderid}");
            //}
            //payment.Order = order;
            //_dbContext.PaymentDetails.Add(payment);
            //return payment;

            return await Task.FromResult(payment);
        }
    }
}
