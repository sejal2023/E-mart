using E_mart.Dtos;
using E_mart.Models;
using E_mart.Repositories;

namespace E_mart.Services
{
    public class OrderService : IOrderService
    {
        private readonly eMartDbContext _eMartDbContext;

        public OrderService(eMartDbContext context)
        {
            _eMartDbContext = context;
        }

        public IEnumerable<OrderDTO> GetAllOrders()
        {
            return _eMartDbContext.Orders.Select(o => new OrderDTO
            {
                OrderId = o.Orderid,
                Amount = o.Amount,
                OrderDate = o.OrderDate,
                PaymentMethod = o.PaymentMethod,
                Status = o.Status,
                CartId = o.Cartid,
                UserId = o.Userid
            }).ToList();
        }

        public OrderDTO GetOrderById(int id)
        {
            var order = _eMartDbContext.Orders.Find(id);
            if (order == null) return null;
            return new OrderDTO
            {
                OrderId = order.Orderid,
                Amount = order.Amount,
                OrderDate = order.OrderDate,
                PaymentMethod = order.PaymentMethod,
                Status = order.Status,
                CartId = order.Cartid,
                UserId = order.Userid
            };
        }

        public void CreateOrder(OrderDTO orderDto)
        {
            var order = new Order
            {
                Amount = orderDto.Amount,
                OrderDate = orderDto.OrderDate,
                PaymentMethod = orderDto.PaymentMethod,
                Status = orderDto.Status,
                Cartid = orderDto.CartId,
                Userid = orderDto.UserId
            };
            _eMartDbContext.Orders.Add(order);
            _eMartDbContext.SaveChanges();
        }

        public void UpdateOrder(int id, OrderDTO orderDto)
        {
            var order = _eMartDbContext.Orders.Find(id);
            if (order == null) return;

            order.Amount = orderDto.Amount;
            order.OrderDate = orderDto.OrderDate;
            order.PaymentMethod = orderDto.PaymentMethod;
            order.Status = orderDto.Status;
            order.Cartid = orderDto.CartId;
            order.Userid = orderDto.UserId;

            _eMartDbContext.SaveChanges();
        }

        public void DeleteOrder(int id)
        {
            var order = _eMartDbContext.Orders.Find(id);
            if (order == null) return;

            _eMartDbContext.Orders.Remove(order);
            _eMartDbContext.SaveChanges();
        }
    }
}
